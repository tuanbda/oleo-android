package com.framgia.oleo.data.source


import com.framgia.oleo.data.source.model.Message
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.ValueEventListener

class MessagesRepository(private val messsagesDataSource: MesssagesDataSource) : MesssagesDataSource {

    override fun getListBoxChat(userId: String, childEventListener: ChildEventListener) {
        messsagesDataSource.getListBoxChat(userId, childEventListener)
    }

    override fun getLastMessage(userId: String, roomId: String, childEventListener: ChildEventListener) {
        messsagesDataSource.getLastMessage(userId, roomId, childEventListener)
    }

    override fun getMessage(userId: String, roomId: String, childEventListener: ChildEventListener) {
        return messsagesDataSource.getMessage(userId, roomId, childEventListener)
    }

    override fun getOldMessage(userId: String, roomId: String, oldMessageId: String,
        valueEventListener: ValueEventListener) {
        messsagesDataSource.getOldMessage(userId, roomId, oldMessageId, valueEventListener)
    }

    override fun getImageProfile(userId: String, valueEventListener: ValueEventListener) {
        messsagesDataSource.getImageProfile(userId, valueEventListener)
    }

    override fun getMessageId(userId: String, boxId: String): String = messsagesDataSource.getMessageId(userId, boxId)

    override fun sendMessage(userId: String, boxId: String, messageId: String, message: Message) {
        messsagesDataSource.sendMessage(userId, boxId, messageId, message)
    }
}
