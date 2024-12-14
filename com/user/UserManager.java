package com.user;

import com.lib.comp.NumberInput;
import com.lib.comp.Label;
import com.lib.util.Brain;
import com.lib.util.Data;
import com.lib.util.Boolean;
import com.settings.Settings;

public class UserManager {
    private User[] users;

    private Brain b;

    public UserManager (NumberInput[] inputs, Label[] outputs) throws NullPointerException {
        NullPointerException e = Data.checkArr(inputs);
        if (e!=null)
            throw e;
        e = Data.checkArr(outputs);
        if (e!=null)
            throw e;

        b = new Brain();
        initializeUsers(inputs, outputs);
    }

    public int getNearest () {
        int[] vals = new int[users.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = users[i].getValue();
        }

        int out = b.getNearest(vals, Settings.secretNum);
        return out;
    }

    public boolean play () {
        boolean success = true;
        for (User u : users) {
            success = Boolean.min(success, u.next());
        }
        return success;
    }

    private void initializeUsers (NumberInput[] inputs, Label[] outputs) {
        users = new User[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            users[i] = new User(inputs[i], outputs[i], i+1);
        }
    }

    public Brain getB() {
        return b;
    }

    public User[] getUsers () {
        return users;
    }

}
