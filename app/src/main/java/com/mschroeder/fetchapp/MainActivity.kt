package com.mschroeder.fetchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mschroeder.fetchapp.data.models.FetchResponseModel
import com.mschroeder.fetchapp.data.models.FetchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val fetchViewModel: FetchViewModel = hiltViewModel()

            LaunchedEffect(Unit) {
                // Fetch list data
                fetchViewModel.getList()
            }

            Surface(modifier = Modifier.fillMaxSize()) {
                DrawListPage(fetchViewModel)
            }
        }
    }

    @Composable
    private fun DrawListPage(fetchViewModel: FetchViewModel) {
        val listData by fetchViewModel.listData.observeAsState()
        val loading by fetchViewModel.loading.observeAsState(true)
        val error by fetchViewModel.error.observeAsState(null)

        if (loading) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (error != null) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val undefinedText = stringResource(R.string.errorUndefined)
                Text(text = stringResource(R.string.errorException,
                    error?.localizedMessage ?: undefinedText))
            }
        } else if (listData == null) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Should never get here, but adding it in to be sure
                Text(text = stringResource(R.string.errorListFailed))
            }
        } else {
            val list = createSortedList(listData!!)
            LazyColumn(modifier = Modifier
                .padding(all = dimensionResource(R.dimen.pagePadding))) {
                list.entries.forEach { (listId, itemList) ->
                    item {
                        // Print out header List ID
                        Text(text = stringResource(R.string.listId, listId))
                    }
                    items(itemList) { item ->
                        // Print out list item
                        Text(text = item.name!!,
                            modifier = Modifier.padding(
                                horizontal = dimensionResource(R.dimen.listItemPadding)
                            )
                        )
                    }
                }
            }
        }
    }

    private fun createSortedList(items: List<FetchResponseModel>): Map<Int, List<FetchResponseModel>> {
        return items
            .groupBy { it.listId } // group by "listId"
            .toSortedMap() // get the ListIDs in order
            .mapValues { (_, itemList) ->
                itemList
                    .filter { !it.name.isNullOrEmpty() } // Filter out any items where "name" is blank or null
                    .sortedBy { it.id } // Order by id number as all the names are Item {id}
            }
    }
}