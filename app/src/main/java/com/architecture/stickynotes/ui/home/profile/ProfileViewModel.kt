package com.architecture.stickynotes.ui.home.profile

import androidx.lifecycle.ViewModel;
import com.architecture.stickynotes.data.repository.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()

}
