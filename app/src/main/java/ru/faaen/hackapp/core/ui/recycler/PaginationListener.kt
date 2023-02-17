package ru.faaen.hackapp.core.ui.recycler

import androidx.recyclerview.widget.RecyclerView

abstract class PaginationListener(
    private val layoutManager: RecyclerView.LayoutManager,
    private val pageSize: Int = DEFAULT_PAGE_SIZE
): RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (isLoading() || isLastPage()) return

        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = getLastVisibleItemPosition()
        val updatePosition = totalItemCount - (pageSize / 2)

        if (lastVisibleItemPosition >= updatePosition) loadNextPage()
    }

    abstract fun loadNextPage()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean

    abstract fun getLastVisibleItemPosition(): Int

    companion object {
        private const val DEFAULT_PAGE_SIZE = 50
    }
}