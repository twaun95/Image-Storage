package com.twaun95.presentation.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.LifecycleOwner
import com.twaun95.presentation.R
import com.twaun95.presentation.databinding.FragmentDialogCommonBinding
import com.twaun95.presentation.extensions.setVisible

class CommonDialog private constructor() : DialogFragment() {

    private lateinit var binding: FragmentDialogCommonBinding
    private val title by lazy { arguments?.getString(TITLE).orEmpty() }
    private val description by lazy { arguments?.getString(DESCRIPTION).orEmpty() }
    private val negativeButtonName by lazy {
        arguments?.getString(NEGATIVE_BUTTON_NAME) ?: getString(R.string.cancel)
    }
    private val negativeButtonEnable by lazy {
        arguments?.getBoolean(NEGATIVE_BUTTON_ENABLE) ?: true
    }
    private val positiveButtonName by lazy {
        arguments?.getString(POSITIVE_BUTTON_NAME) ?: getString(R.string.confirm)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogCommonBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = title
        binding.tvDescription.text = description
        binding.tvNegative.setVisible(negativeButtonEnable)
        if (!negativeButtonEnable) binding.tvPositive.setBackgroundResource(R.drawable.bg_dialog_single_positive)
        binding.tvNegative.text = negativeButtonName
        binding.tvPositive.text = positiveButtonName

        binding.tvNegative.setOnClickListener {
            setFragmentResult(RESULT, bundleOf(RESULT_NEGATIVE_ACTION to true))
            dismiss()
        }
        binding.tvPositive.setOnClickListener {
            setFragmentResult(RESULT, bundleOf(RESULT_POSITIVE_ACTION to true))
            dismiss()
        }
    }

    companion object {
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val NEGATIVE_BUTTON_NAME = "negative_button_name"
        private const val NEGATIVE_BUTTON_ENABLE = "negative_button_enable"
        private const val POSITIVE_BUTTON_NAME = "positive_button_name"
        private const val TAG = "CommonDialog"

        private const val RESULT = "result"
        private const val RESULT_NEGATIVE_ACTION = "result_negative_action"
        private const val RESULT_POSITIVE_ACTION = "result_positive_action"

        fun show(
            fragmentManager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            title: String,
            description: String,
            negativeButtonEnable: Boolean = true,
            negativeButtonName: String,
            negativeAction: () -> Unit = {},
            positiveButtonName: String,
            positiveAction: () -> Unit = {},
        ) {
            return CommonDialog().apply {
                arguments = bundleOf(
                    TITLE to title,
                    DESCRIPTION to description,
                    NEGATIVE_BUTTON_NAME to negativeButtonName,
                    NEGATIVE_BUTTON_ENABLE to negativeButtonEnable,
                    POSITIVE_BUTTON_NAME to positiveButtonName
                )
            }.also {
                fragmentManager.setFragmentResultListener(RESULT, lifecycleOwner) { _, bundle ->
                    when {
                        bundle.getBoolean(RESULT_NEGATIVE_ACTION, false) -> {
                            negativeAction.invoke()
                        }
                        bundle.getBoolean(RESULT_POSITIVE_ACTION, false) -> {
                            positiveAction.invoke()
                        }
                    }
                }
            }.show(fragmentManager, TAG)
        }
    }
}