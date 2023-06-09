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
import com.twaun95.presentation.extensions.setOnSingleClickListener
import com.twaun95.presentation.extensions.setVisible

class CommonDialog private constructor() : DialogFragment() {

    private lateinit var binding: FragmentDialogCommonBinding
    private val description by lazy { arguments?.getString(DESCRIPTION).orEmpty() }
    private val negativeName by lazy {
        arguments?.getString(NEGATIVE_NAME) ?: getString(R.string.cancel)
    }
    private val negativeEnable by lazy {
        arguments?.getBoolean(NEGATIVE_ENABLE) ?: true
    }
    private val positiveName by lazy {
        arguments?.getString(POSITIVE_NAME) ?: getString(R.string.confirm)
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

        binding.tvDescription.text = description
        binding.tvNegative.setVisible(negativeEnable)
        binding.tvNegative.text = negativeName
        if (!negativeEnable) binding.tvPositive.setBackgroundResource(R.drawable.bg_dialog_single_positive)
        binding.tvPositive.text = positiveName

        binding.tvNegative.setOnSingleClickListener {
            setFragmentResult(RESULT, bundleOf(RESULT_NEGATIVE_ACTION to true))
            dismiss()
        }
        binding.tvPositive.setOnSingleClickListener {
            setFragmentResult(RESULT, bundleOf(RESULT_POSITIVE_ACTION to true))
            dismiss()
        }
    }

    companion object {
        private const val DESCRIPTION = "description"
        private const val NEGATIVE_NAME = "negative_name"
        private const val NEGATIVE_ENABLE = "negative_enable"
        private const val POSITIVE_NAME = "positive_name"
        private const val TAG = "CommonDialog"
        private const val RESULT = "result"
        private const val RESULT_NEGATIVE_ACTION = "result_negative_action"
        private const val RESULT_POSITIVE_ACTION = "result_positive_action"

        fun show(
            fragmentManager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            description: String,
            negativeEnable: Boolean = true,
            negativeName: String? = "",
            negativeAction: () -> Unit = {},
            positiveName: String,
            positiveAction: () -> Unit = {},
        ) {
            return CommonDialog().apply {
                arguments = bundleOf(
                    DESCRIPTION to description,
                    NEGATIVE_NAME to negativeName,
                    NEGATIVE_ENABLE to negativeEnable,
                    POSITIVE_NAME to positiveName
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