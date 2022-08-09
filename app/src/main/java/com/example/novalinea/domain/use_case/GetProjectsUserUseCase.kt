package com.example.novalinea.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.novalinea.common.Constants.LIMIT_PROJECTS_TAPE
import com.example.novalinea.data.paging.ProjectsPagingSource
import com.example.novalinea.domain.model.TypesPagingProjects
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope

class GetProjectsUserUseCase(
	private val db: FirebaseFirestore
) {

	operator fun invoke(
		userID: String,
		viewModelScope: CoroutineScope
	) = Pager(
		PagingConfig(
			pageSize = LIMIT_PROJECTS_TAPE
		)
	) {
		ProjectsPagingSource(db, TypesPagingProjects.PROFILE, userID)
	}.flow.cachedIn(viewModelScope)
}