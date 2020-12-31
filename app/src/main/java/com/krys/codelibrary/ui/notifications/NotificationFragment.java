package com.krys.codelibrary.ui.notifications;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;
import androidx.fragment.app.Fragment;

import com.krys.codelibrary.R;
import com.krys.codelibrary.customviews.BoldButton;
import com.krys.codelibrary.utils.CommonUtils;
import com.krys.codelibrary.utils.NotificationUtils;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    private BoldButton btnSimpleNoti;
    private BoldButton btnBigTextNoti;
    private BoldButton btnBigPictureNoti;
    private BoldButton btnWithReplyNoti;
    private BoldButton btnInboxNoti;
    private BoldButton btnMsgNoti;
    private BoldButton btnHeadsUpNoti;
    private BoldButton btnProgNoti;
    private BoldButton btnCustomNoti;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        findViewById(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setClickListners();
    }

    private void findViewById(View root) {
        btnSimpleNoti = root.findViewById(R.id.btnSimpleNoti);
        btnBigTextNoti = root.findViewById(R.id.btnBigTextNoti);
        btnBigPictureNoti = root.findViewById(R.id.btnBigPictureNoti);
        btnWithReplyNoti = root.findViewById(R.id.btnWithReplyNoti);
        btnInboxNoti = root.findViewById(R.id.btnInboxNoti);
        btnHeadsUpNoti = root.findViewById(R.id.btnHeadsUpNoti);
        btnMsgNoti = root.findViewById(R.id.btnMsgNoti);
        btnCustomNoti = root.findViewById(R.id.btnCustomNoti);
        btnProgNoti = root.findViewById(R.id.btnProgNoti);
    }

    private void setClickListners() {
        btnSimpleNoti.setOnClickListener(v -> {
            NotificationUtils.showSimpleNotification(getActivity(),
                    "Hello There!",
                    "Welcome to CodeLibrary");
        });
        btnBigTextNoti.setOnClickListener(v -> {
            NotificationUtils.showBigTextNotification(getActivity(),
                    "Important Update",
                    "Hello User , if you like our work consider donating some amount by tapping 'Buy me a coffee' in the app");
        });
        btnBigPictureNoti.setOnClickListener(v -> {
            NotificationUtils.showBigPictureNotification(getActivity(),
                    "Updates",
                    "New System Update is out with lots of features",
                     CommonUtils.getIcon(getActivity(), R.drawable.header_image),
                     CommonUtils.getIcon(getActivity(), R.drawable.dummy_image));
        });
        btnInboxNoti.setOnClickListener(v -> {
            NotificationUtils.showInboxNotification(getActivity(),
                    "Riya Sharma",
                    getMessagesList());
        });
        btnWithReplyNoti.setOnClickListener(v -> {
            NotificationUtils.showNotificationWithReply(getActivity(),
                    "Riya Sharma",
                    "Hey are you there?");
        });
        btnMsgNoti.setOnClickListener(v -> {
            NotificationUtils.showMessageNotification(getActivity(),
                    "Chats",
                    "Group chats",
                    CommonUtils.getIcon(getActivity(), R.drawable.dummy_user1));
        });
        btnHeadsUpNoti.setOnClickListener(v -> {
            NotificationUtils.showHeadsUpNotification(getActivity(),
                    "Incoming call",
                    "Riya is calling you");
        });
        btnCustomNoti.setOnClickListener(v -> {
            NotificationUtils.showCustomNotification(getActivity(),
                    "This is title",
                    "This is message",
                    CommonUtils.getIcon(getActivity(), R.drawable.dummy_image));
        });
        btnProgNoti.setOnClickListener(v -> {
            NotificationUtils.showProgressNotification(getActivity(),
                    "File Download",
                    "Downloading in progress");
        });
    }

    private ArrayList<String> getMessagesList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Hey there?!");
        list.add("Listen to me");
        list.add("Are you free tonight");
        list.add("Let's go for ride");
        list.add("See you soon");
        return list;
    }


}