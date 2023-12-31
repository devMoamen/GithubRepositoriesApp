package com.githubRepository.domain.model.general

sealed class RepositoryFilterType() {
    data object LastWeekFilter : RepositoryFilterType()
    data object LastMonthFilter : RepositoryFilterType()
    data object LastDayFilter : RepositoryFilterType()
}
