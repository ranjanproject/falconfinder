package com.example.falconfinder.di

import androidx.fragment.app.Fragment
import com.example.falconfinder.ui.MainActivity
import com.example.falconfinder.ui.listeners.DialogEventListeners
import com.example.falconfinder.ui.listeners.FindFalconClickListener
import com.example.falconfinder.ui.listeners.ItemClickListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent


@Module
@InstallIn(FragmentComponent::class)
class ItemClickListenerModule{
    @Provides
    fun getItemClickBack(fragment: Fragment) = fragment as ItemClickListener
}


@Module
@InstallIn(ActivityComponent::class)
object FindFalconClickListenerModule{
    @Provides
    fun provideCallBack(activity: MainActivity) = activity as FindFalconClickListener
}

@Module
@InstallIn(FragmentComponent::class)
class DialogInterfaceListenerModule{
    @Provides
    fun provideDialogInterFaceCallBack(fragment: Fragment) = fragment as DialogEventListeners
}

