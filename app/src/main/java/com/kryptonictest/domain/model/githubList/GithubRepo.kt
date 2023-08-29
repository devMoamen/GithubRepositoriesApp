package com.kryptonictest.domain.model.githubList

data class GithubRepo(
    val id: Int = 0,
    val name: String? = "",
    val stargazers_count: Int = 0,
    val created_at: String?,
    val description: String?,
    val fork: Boolean? = false,
    val forks: Int,
    val forks_count: Int,
    val full_name: String? = "",
    val git_commits_url: String? = "",
    val has_pages: Boolean? = false,
    val has_projects: Boolean? = false,
    val html_url: String? = "",
    val language: String? = "",
    val node_id: String? = "",
    val owner: Owner? = null,
    val size: Int,
    val ssh_url: String? = "",
    val teams_url: String? = "",
    val topics: List<String>? = arrayListOf(),
    val updated_at: String? = "",
    val url: String? = "",
    val watchers_count: Int?,
) {

    data class Owner(
        val id: Int = 0,
        val login: String? = "",
        val avatar_url: String? = "",
        val followers_url: String? = "",
        val following_url: String? = "",
        val gists_url: String? = "",
        val gravatar_id: String? = "",
        val html_url: String? = "",
        val repos_url: String? = "",
        val subscriptions_url: String? = "",
        val type: String? = "",
        val url: String? = ""
    )
}