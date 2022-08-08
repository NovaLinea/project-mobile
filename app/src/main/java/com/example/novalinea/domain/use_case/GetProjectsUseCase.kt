package com.example.novalinea.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.novalinea.common.Constants.LIMIT_PROJECTS_TAPE
import com.example.novalinea.data.pagging.ProjectsPagingSource
import kotlinx.coroutines.CoroutineScope

class GetProjectsUseCase(
	private val source: ProjectsPagingSource
) {

	operator fun invoke(viewModelScope: CoroutineScope) = Pager(
		PagingConfig(
			pageSize = LIMIT_PROJECTS_TAPE
		)
	) { source }.flow.cachedIn(viewModelScope)
}