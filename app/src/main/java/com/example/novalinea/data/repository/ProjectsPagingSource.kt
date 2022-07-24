package com.example.novalinea.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.novalinea.common.Constants
import com.example.novalinea.common.Constants.PROJECTS_COLLECTION
import com.example.novalinea.domain.model.ProjectCreator
import com.example.novalinea.domain.model.ProjectTape
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class ProjectsPagingSource(
	private val db: FirebaseFirestore
) : PagingSource<Int, ProjectTape>() {

	override fun getRefreshKey(state: PagingState<Int, ProjectTape>): Int? {
		TODO("Not yet implemented")
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProjectTape> {
		val page: Int = params.key ?: 1
		val pageSize: Int = params.loadSize

		val projects = db.collection(PROJECTS_COLLECTION)
			.orderBy(Constants.VIEWS_PROJECT_FIELD, Query.Direction.DESCENDING)
			//.limit(Constants.LIMIT_PROJECTS_TAPE.toLong())
			.get().await().map { document ->
				val project = document.toObject(ProjectTape::class.java)
				project.id = document.id

				if (project.creatorID != null) {
					val creator = db.collection(Constants.USERS_COLLECTION)
						.document(project.creatorID).get().await()
						.toObject(ProjectCreator::class.java)
					project.creatorName = creator?.name.toString()
					project.creatorPhoto = creator?.photo.toString()
				}

				project
			}

		return LoadResult.Page(projects, 1, 2)
	}
}