package com.stellarworker.learninghelper.ui.classes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.stellarworker.learninghelper.R
import com.stellarworker.learninghelper.databinding.FragmentClassesBinding
import com.stellarworker.learninghelper.domain.ClassesEx
import com.stellarworker.learninghelper.domain.ClassesFragmentMessage
import com.stellarworker.learninghelper.utils.makeSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassesFragment : Fragment() {
    private var _binding: FragmentClassesBinding? = null
    private val binding get() = _binding!!
    private val classesFragmentViewModel: ClassesFragmentViewModel by viewModel()
    private val classesFragmentAdapter = ClassesFragmentAdapter(
        openSkype = {
            val message = getString(R.string.no_skype_found)
            val mapIntent = Intent(Intent.ACTION_VIEW)
            mapIntent.setPackage(getString(R.string.skype_package))
            if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClassesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerView()
        classesFragmentViewModel.requestClassesInfo()
    }

    private fun initViewModel() {
        classesFragmentViewModel.messagesLiveData.observe(viewLifecycleOwner) { message ->
            processMessages(message)
        }
    }

    private fun initRecyclerView() {
        binding.fragmentClassesList.adapter = classesFragmentAdapter
    }

    private fun processMessages(classesFragmentMessage: ClassesFragmentMessage) {
        with(classesFragmentMessage) {
            when (this) {
                is ClassesFragmentMessage.ClassesMessage -> showInfo(classes)
                is ClassesFragmentMessage.InfoSnackBar -> makeSnackbar(
                    view = binding.root,
                    text = text
                )
                is ClassesFragmentMessage.InfoToast -> Toast.makeText(context, text, length).show()
            }
        }
    }

    private fun showInfo(classes: List<ClassesEx>) {
        classesFragmentAdapter.setData(classes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}