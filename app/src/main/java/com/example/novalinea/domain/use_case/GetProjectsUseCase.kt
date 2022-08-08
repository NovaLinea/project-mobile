package com.example.novalinea.domain.use_case

import androidx.paging.cachedIn
import com.example.novalinea.domain.repository.ProjectRepository
import kotlinx.coroutines.CoroutineScope

class GetProjectsUseCase(
	private val repository: ProjectRepository
) {

	operator fun invoke(viewModelScope: CoroutineScope) = repository.getProjects().cachedIn(viewModelScope)
}