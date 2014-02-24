/*
 * Copyright 2013, Green Halo Labs LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License.
 * You may obtain a copy of the License in the LICENSE file, or at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.greenhalolabs.emailautocompletetextview.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountUtil {

    /**
     * Retrieves a list of e-mails on the device
     *
     * @return A list of emails
     */
    public static List<String> getAccountEmails(Context context) {
        
        final List<String> emailList = new ArrayList<String>();
        final Account[] accounts = AccountManager.get(context).getAccounts();

        for (Account account : accounts) {
            if (isEmail(account.name) && !emailList.contains(account.name)) {
                emailList.add(account.name);
            }
        }

        return emailList;
    }

    private static boolean isEmail(String email) {

        if (TextUtils.isEmpty(email)) {
            return false;
        }

        final Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        final Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

}
