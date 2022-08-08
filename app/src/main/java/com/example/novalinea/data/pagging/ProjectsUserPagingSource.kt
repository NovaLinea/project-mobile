package com.example.novalinea.data.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.novalinea.common.Constants.CREATED_AT_FIELD
import com.example.novalinea.common.Constants.CREATOR_ID_PROJECT_FIELD
import com.example.novalinea.common.Constants.LIMIT_PROJECTS_TAPE
import com.example.novalinea.common.Constants.PROJECTS_COLLECTION
import com.example.novalinea.common.Constants.USERS_COLLECTION
import com.example.novalinea.domain.model.ProjectCreator
import com.example.novalinea.domain.model.ProjectTape
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.DESCENDING
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class ProjectsUserPagingSource(
	private val db: FirebaseFirestore,
	private val userID: String
) : PagingSource<QuerySnapshot, ProjectTape>() {

	override fun getRefreshKey(state: PagingState<QuerySnapshot, ProjectTape>): QuerySnapshot? = null

	override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, ProjectTape> {
		return try {
			val currentPage = params.key ?: db.collection(PROJECTS_COLLECTION)
				.whereEqualTo(CREATOR_ID_PROJECT_FIELD, userID)
				.orderBy(CREATED_AT_FIELD, DESCENDING)
				.limit(LIMIT_PROJECTS_TAPE.toLong())
				.get().await()

			if (currentPage.size() == 0) {
				LoadResult.Page(
					data = emptyList(),
					prevKey = null,
					nextKey = null
				)
			}
			else {
				val lastVisibleProject = currentPage.documents[currentPage.size() - 1]

				val nextPage = db.collection(PROJECTS_COLLECTION)
					.whereEqualTo(CREATOR_ID_PROJECT_FIELD, userID)
					.orderBy(CREATED_AT_FIELD, DESCENDING)
					.limit(LIMIT_PROJECTS_TAPE.toLong())
					.startAfter(lastVisibleProject)
					.get().await()

				val detailsProjectData = currentPage.map { document ->
					val project = document.toObject(ProjectTape::class.java)
					project.id = document.id

					if (project.creatorID != null) {
						val creator = db.collection(USERS_COLLECTION)
							.document(project.creatorID).get().await()
							.toObject(ProjectCreator::class.java)

						project.creatorName = creator?.name.toString()
						project.creatorPhoto = creator?.photo.toString()
						project.creatorVerify = creator?.verify == true
					}

					project
				}

				LoadResult.Page(
					data = detailsProjectData,
					prevKey = null,
					nextKey = nextPage
				)
			}
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}
}