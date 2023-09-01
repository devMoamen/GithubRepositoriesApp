package com.kryptonictest.app.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kryptonictest.app.adapters.loadMoreAdapter.LoadingMoreAdapter
import com.kryptonictest.utils.interfaces.OnRepositoryItemClickListener
import com.kryptonictest.app.adapters.repositoryAdapter.RepositoryListAdapter
import com.kryptonictest.app.bases.BaseViewModel
import com.kryptonictest.domain.model.general.RepositoryFilterType
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.domain.repository.GithubRepositoryRepo
import com.kryptonictest.network.ApiParamsConstant.PARAM_ORDER
import com.kryptonictest.network.ApiParamsConstant.PARAM_ORDER_DESC
import com.kryptonictest.network.ApiParamsConstant.PARAM_PAGE
import com.kryptonictest.network.ApiParamsConstant.PARAM_PER_PAGE
import com.kryptonictest.network.ApiParamsConstant.PARAM_PER_PAGE_VALUE
import com.kryptonictest.network.ApiParamsConstant.PARAM_Q
import com.kryptonictest.network.ApiParamsConstant.PARAM_SORT
import com.kryptonictest.network.ApiParamsConstant.PARAM_SORT_STARS
import com.kryptonictest.utils.general.GeneralMethods
import com.kryptonictest.utils.general.SingleLiveEvent
import com.kryptonictest.utils.interfaces.OnLoadingMoreListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val githubRepositoryRepo: GithubRepositoryRepo) :
    BaseViewModel(), OnRepositoryItemClickListener {

    private var page = 1
    private var hasMoreItems = false
    private var filterRange = GeneralMethods.getLastMonthData()

    private val repositoryJob: Job? = null

    private val openRepoDetails = SingleLiveEvent<GithubRepo>()
    fun getOpenRepoDetails(): SingleLiveEvent<GithubRepo> {
        return openRepoDetails
    }

    val isRefreshAdapter = MutableLiveData<Boolean>().apply { value = false }
    val showEmptyView = MutableLiveData<Boolean>().apply { value = false }

    private val loadingMoreAdapter = LoadingMoreAdapter()

    private val githubRepoAdapter = RepositoryListAdapter().apply {
        setOnItemClickListener(this@HomeViewModel)
    }

    val concatAdapter = MutableLiveData<ConcatAdapter>().apply {
        value = ConcatAdapter(githubRepoAdapter)
    }

    init {
        isLoading.postValue(true)
        getRepositories()
    }

    private fun resetData() {
        page = 1
        githubRepoAdapter.removeAll()
    }

    private fun getRepositories() {
        val params = HashMap<String, Any>().apply {
            this[PARAM_Q] = "created:>$filterRange"
            this[PARAM_PAGE] = page
            this[PARAM_SORT] = PARAM_SORT_STARS
            this[PARAM_ORDER] = PARAM_ORDER_DESC
            this[PARAM_PER_PAGE] = PARAM_PER_PAGE_VALUE
        }
        viewModelScope.launch(Dispatchers.IO) {
            githubRepositoryRepo.searchGithubRepository(params).onStart {
                showEmptyView.postValue(false)
            }.catch {
                isLoading.postValue(false)
                showEmptyView.postValue(true)
                showErrorMessage.postValue(it.message)
                removeLoadingAdapter()
            }.collect {
                if (repositoryJob?.isCancelled == true) return@collect
                isRefreshLoading.postValue(false)
                isRefreshAdapter.postValue(true)
                isLoading.postValue(false)
                if (page == 1 && it.items.isEmpty()) {
                    showEmptyView.postValue(true)
                }

                hasMoreItems = it.total_count != githubRepoAdapter.itemCount

                viewModelScope.launch {
                    removeLoadingAdapter()
                    handleGithubRepositoryResult(it.items)
                }
            }
        }
    }

    private fun handleGithubRepositoryResult(items: ArrayList<GithubRepo>) {
        githubRepoAdapter.append(items)
    }

    override fun openRepositoryDetails(item: GithubRepo) {
        openRepoDetails.value = item
    }

    val onRefreshingListener = SwipeRefreshLayout.OnRefreshListener {
        getDataWithReset()
    }

    private fun getDataWithReset() {
        resetData()
        repositoryJob?.cancel()
        getRepositories()
    }

    val onLoadingMoreListener = object : OnLoadingMoreListener {
        override fun onLoadingMore() {
            page++
            showLoadingAdapter()
            getRepositories()
        }
    }

    private fun showLoadingAdapter() {
        concatAdapter.value!!.addAdapter(loadingMoreAdapter)
    }

    private fun removeLoadingAdapter() {
        concatAdapter.value!!.removeAdapter(loadingMoreAdapter)
    }

    fun onSelectFilter(filterType: RepositoryFilterType) {
        filterRange = when (filterType) {
            is RepositoryFilterType.LastDayFilter -> {
                GeneralMethods.getLastDayData()
            }

            is RepositoryFilterType.LastWeekFilter -> {
                GeneralMethods.getLastWeekData()
            }

            is RepositoryFilterType.LastMonthFilter -> {
                GeneralMethods.getLastMonthData()
            }
        }
        getDataWithReset()
    }
}