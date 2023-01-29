package com.stellarworker.learninghelper.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.stellarworker.learninghelper.R
import com.stellarworker.learninghelper.databinding.FragmentMainBinding
import com.stellarworker.learninghelper.domain.AppMessage
import com.stellarworker.learninghelper.domain.ClassesEx
import com.stellarworker.learninghelper.domain.ClassesInfo
import com.stellarworker.learninghelper.domain.Homework
import com.stellarworker.learninghelper.utils.convertLongToTime
import com.stellarworker.learninghelper.utils.getCurrentTime
import com.stellarworker.learninghelper.utils.makeSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainFragmentViewModel: MainFragmentViewModel by viewModel()
    private val classesAdapter = ClassesAdapter(
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
    private val homeworkAdapter = HomeworkAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerViews()
        mainFragmentViewModel.requestClassesInfo()
    }

    private fun initViewModel() {
        mainFragmentViewModel.messagesLiveData.observe(viewLifecycleOwner) { message ->
            processMessages(message)
        }
    }

    private fun initRecyclerViews() {
        with(binding) {
            fragmentMainClassesList.adapter = classesAdapter
            fragmentMainHomeworkList.adapter = homeworkAdapter
        }
    }

    private fun processMessages(appMessage: AppMessage) {
        with(appMessage) {
            when (this) {
                is AppMessage.ClassesMessage -> showInfo(classesInfo)
                is AppMessage.InfoSnackBar -> makeSnackbar(
                    view = binding.root,
                    text = text
                )
                is AppMessage.InfoToast -> Toast.makeText(context, text, length).show()
            }
        }
    }

    private fun showInfo(classesInfo: ClassesInfo) {
        showExamsInfo(classesInfo.examsDate)
        showClassesInfo(classesInfo.classes)
        showHomeworksInfo(classesInfo.homeworks)
    }

    private fun showExamsInfo(examsDate: Long) {
        val currentDate = getCurrentTime()
        val stringDate = convertLongToTime(examsDate - currentDate, "dd.HH.mm")
        with(binding) {
            fragmentMainExamsCountdownDaysFirstText.text = stringDate[0].toString()
            fragmentMainExamsCountdownDaysSecondText.text = stringDate[1].toString()
            fragmentMainExamsCountdownHoursFirstText.text = stringDate[3].toString()
            fragmentMainExamsCountdownHoursSecondText.text = stringDate[4].toString()
            fragmentMainExamsCountdownMinutesFirstText.text = stringDate[6].toString()
            fragmentMainExamsCountdownMinutesSecondText.text = stringDate[7].toString()
        }
    }

    private fun showClassesInfo(classes: List<ClassesEx>) {
        binding.fragmentMainClassesLineValue.text =
            getString(R.string.fragment_main_classes_line_value, classes.size)
        classesAdapter.setData(classes)
    }

    private fun showHomeworksInfo(homeworks: List<Homework>) {
        homeworkAdapter.setData(homeworks)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}