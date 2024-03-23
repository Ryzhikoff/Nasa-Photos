package evgeniy.ryzhikov.feature_settings.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import evgeniy.ryzhikov.core.domain.SettingProvider
import evgeniy.ryzhikov.feature_settings.R
import evgeniy.ryzhikov.feature_settings.databinding.FragmentSettingsBinding
import evgeniy.ryzhikov.feature_settings.di.SettingComponentProvider
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var settingProvider: SettingProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        (requireActivity().application as SettingComponentProvider)
            .getSettingComponent()
            .inject(this)

        initSliders()
    }

    private fun initSliders() = with(binding) {
        sliderPageSize.apply {
            value = settingProvider.pageSize
            addOnChangeListener { _, value, _ ->
                settingProvider.pageSize = value
            }
        }

        sliderInitPageSize.apply {
            value = settingProvider.initialLoadSize
            addOnChangeListener { _, value, _ ->
                settingProvider.initialLoadSize = value
            }
        }

        sliderBufferSize.apply {
            value = settingProvider.bufferSize
            addOnChangeListener { _, value, _ ->
                settingProvider.bufferSize = value
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}