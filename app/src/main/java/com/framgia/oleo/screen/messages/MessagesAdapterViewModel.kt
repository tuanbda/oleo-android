package com.framgia.oleo.screen.messages

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.framgia.oleo.data.source.MessagesRepository
import com.framgia.oleo.data.source.model.Message
import com.framgia.oleo.data.source.model.User
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MessagesAdapterViewModel(
    private val messagesRepository: MessagesRepository
) : BaseObservable() {

    private var message = MutableLiveData<Message>()

    private var image = ""

    fun setMessage(userId: String, boxId: String): MutableLiveData<Message> {
        messagesRepository.getLastMessage(userId, boxId, object : ChildEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, p1: String?) {}

            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                message.value = dataSnapshot.getValue(Message::class.java)!!
            }

            override fun onChildRemoved(p0: DataSnapshot) {}
        })
        return message
    }

    fun setImageProfile(userId: String) {
        messagesRepository.getImageProfile(userId, object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                image = dataSnapshot.getValue(User::class.java)!!.image
                notifyPropertyChanged(BR.image)
            }
        })
    }

    @Bindable
    fun getImage() = image
}
