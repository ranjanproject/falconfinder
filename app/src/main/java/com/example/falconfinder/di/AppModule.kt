package com.example.falconfinder.di

import android.app.Activity
import android.content.DialogInterface
import androidx.fragment.app.Fragment
import com.example.falconfinder.MainActivity
import com.example.falconfinder.ui.DialogEventListeners
import com.example.falconfinder.ui.FindFalconClickListener
import com.example.falconfinder.ui.ItemClickListener
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent


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

