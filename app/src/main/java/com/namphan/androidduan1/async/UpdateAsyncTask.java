package com.namphan.androidduan1.async;

import android.os.AsyncTask;

import com.namphan.androidduan1.model.Note;
import com.namphan.androidduan1.persistence.NoteDao;

;

public class UpdateAsyncTask extends AsyncTask<Note, Void, Void> {

    private NoteDao mNoteDao;

    public UpdateAsyncTask(NoteDao dao) {
        mNoteDao = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        mNoteDao.updateNotes(notes);
        return null;
    }

}