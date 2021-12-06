package com.example.truecreditslist.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.truecreditslist.R
import com.example.truecreditslist.databinding.ActivityMainBinding
import com.example.truecreditslist.databinding.FragmentFirstBinding
import com.example.truecreditslist.getViewModelFactory
import com.example.truecreditslist.ui.MainViewModel

class FirstFragment : Fragment() {

    lateinit var viewDataBinding: FragmentFirstBinding
        private set

    private val viewModel by viewModels<MainViewModel> { getViewModelFactory() }

    private lateinit var adapter: TruecreditPagingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_first, container, false)
        viewDataBinding = FragmentFirstBinding.bind(root).apply {
            viewmodel = viewModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initAdapter()
//        initSwipeToRefresh()
    }
    private fun initAdapter() {
        adapter = TruecreditPagingAdapter()
//        binding.list.adapter = adapter.withLoadStateHeaderAndFooter(
//            header = PostsLoadStateAdapter(adapter),
//            footer = PostsLoadStateAdapter(adapter)
//        )
//
//        lifecycleScope.launchWhenCreated {
//            adapter.loadStateFlow.collect { loadStates ->
//                binding.swipeRefresh.isRefreshing = loadStates.mediator?.refresh is LoadState.Loading
//            }
//        }
//
//        lifecycleScope.launchWhenCreated {
//            model.posts.collectLatest {
//                adapter.submitData(it)
//            }
//        }
//
//        lifecycleScope.launchWhenCreated {
//            adapter.loadStateFlow
//                // Use a state-machine to track LoadStates such that we only transition to
//                // NotLoading from a RemoteMediator load if it was also presented to UI.
//                .asMergedLoadStates()
//                // Only emit when REFRESH changes, as we only want to react on loads replacing the
//                // list.
//                .distinctUntilChangedBy { it.refresh }
//                // Only react to cases where REFRESH completes i.e., NotLoading.
//                .filter { it.refresh is LoadState.NotLoading }
//                // Scroll to top is synchronous with UI updates, even if remote load was triggered.
//                .collect { binding.list.scrollToPosition(0) }
//        }
    }
//
//    private fun initSwipeToRefresh() {
//        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }
//    }
//
//    private fun initSearch() {
//        binding.input.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_GO) {
//                updatedSubredditFromInput()
//                true
//            } else {
//                false
//            }
//        }
//        binding.input.setOnKeyListener { _, keyCode, event ->
//            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
//                updatedSubredditFromInput()
//                true
//            } else {
//                false
//            }
//        }
//    }
//
//    private fun updatedSubredditFromInput() {
//        binding.input.text.trim().toString().let {
//            if (it.isNotBlank()) {
//                model.showSubreddit(it)
//            }
//        }
//    }
//
//    companion object {
//        const val KEY_REPOSITORY_TYPE = "repository_type"
//        fun intentFor(context: Context, type: RedditPostRepository.Type): Intent {
//            val intent = Intent(context, RedditActivity::class.java)
//            intent.putExtra(KEY_REPOSITORY_TYPE, type.ordinal)
//            return intent
//        }
//    }

}