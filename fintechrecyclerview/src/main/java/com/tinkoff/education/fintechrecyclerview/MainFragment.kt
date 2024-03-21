package com.tinkoff.education.fintechrecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tinkoff.education.fintechrecyclerview.databinding.FragmentMainBinding
import com.tinkoff.education.fintechrecyclerview.delegates.DelegatesFragment
import com.tinkoff.education.fintechrecyclerview.itemtouchhelper.ItemTouchHelperFragment
import com.tinkoff.education.fintechrecyclerview.recyclerbasic.BasicRecyclerFragment
import com.tinkoff.education.fintechrecyclerview.notifyapi.NotifyApiFragment

/**
 * @author y.gladkikh
 */
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerviewAdapter.setOnClickListener {
                navigateToFragment(BasicRecyclerFragment(), BasicRecyclerFragment.TAG)
            }
            recyclerNotifyApi.setOnClickListener {
                navigateToFragment(NotifyApiFragment(), NotifyApiFragment.TAG)
            }
            itemTouchHelper.setOnClickListener {
                navigateToFragment(ItemTouchHelperFragment(), ItemTouchHelperFragment.TAG)
            }
            recyclerDelegates.setOnClickListener {
                navigateToFragment(DelegatesFragment(), DelegatesFragment.TAG)
            }
        }
    }

    private fun navigateToFragment(fragment: Fragment, tag: String) {
        parentFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.nav_host_fragment, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val TAG = "MainFragment"
    }
}
