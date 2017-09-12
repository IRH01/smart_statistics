package com.hhly.smartdata.constant;

import java.io.File;

/**
 *
 */
public class SysConstant{
    public static final String SESSION_USER = "session_user";
    public static final String SESSION_USER_ROLES = "session_user_roles";
    public static final String SESSION_USER_PERMS = "session_user_perms";
    public static final String SESSION_VERIFY_CODE = "session_verify_code";
    public static final String SESSION_MENU_KEY = "session_user_menu";
    public static final String SESSION_MENU_MAP = "session_user_menu_map";

    public static final String CHARGE_FILE_TEMPLATE = "file" + File.separator + "charge_file_template.xlsx";
    public static final String CP_AUDIT_PASS = "mail_template" + File.separator + "cp_audit_pass.vm";
    public static final String CP_AUDIT_REJECT = "mail_template" + File.separator + "cp_audit_reject.vm";
    public static final String CP_REGISTER_PASS = "mail_template" + File.separator + "cp_register_pass.vm";
    public static final String CP_GET_PASSWORD = "mail_template" + File.separator + "cp_get_passwd.vm";
}
