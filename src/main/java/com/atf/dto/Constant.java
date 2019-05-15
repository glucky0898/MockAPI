package com.atf.dto;

/**
 * @author
 * @Description:
 * @date : 2018/4/5
 **/
public class Constant {
    public static final String SUCCESS = "0000";
    public static final String FAIL = "1111";

    public static final String PE4 = "PE4";
    public static final String PE5 = "PE5";
    public static final String PE6 = "PE6";
    public static final String SC1 = "SC1";


    public static final int ARCHITECTURE_IS_DELETED = 1;
    public static final int ARCHITECTURE_IS_NOT_DELETED = 0;

    public static final int ARCMETHOD_IS_NOT_DELETED = 0;
    public static final int ARCMETHOD_IS_DELETED = 1;
    public static final int ARCMETHOD_IS_NOT_VISIBLE = 0;
    public static final int ARCMETHOD_IS_VISIBLE = 1;
    public static final int ARCMETHOD_IS_FORBID = 2;

    public static final int ARCCLASS_IS_NOT_DELETED = 0;
    public static final int ARCCLASS_IS_DELETED = 1;
    public static final int ARCCLASS_IS_NOT_VISIBLE = 0;
    public static final int ARCCLASS_IS_VISIBLE = 1;
    public static final int ARCCLASS_IS_FORBID = 2;
    public static final int ARCCLASS_IS_OVERRIDE = 1;

    public static final int AUT_IS_NOT_DELETED = 1;
    public static final int AUT_IS_DELETED = 2;

    public static final int OMCLASS_IS_NOT_DELETED = 0;
    public static final int OMCLASS_IS_DELETED = 1;
    public static final int OMCLASS_IS_NOT_VISIBLE = 0;
    public static final int OMCLASS_IS_VISIBLE = 1;
    public static final int OMCLASS_IS_FORBID = 2;
    public static final int OMCLASS_IS_OVERRIDE = 1;

    public static final int OMMETHOD_IS_NOT_DELETED = 0;
    public static final int OMMETHOD_IS_DELETED = 1;
    public static final int OMMETHOD_IS_NOT_VISIBLE = 0;
    public static final int OMMETHOD_IS_VISIBLE = 1;
    public static final int OMMETHOD_IS_FORBID = 2;

    public static final int RECORDER_EXECUTE_STATUS_INIT = 10;
    public static final int RECORDER_EXECUTE_STATUS_NO_DISTRIBUTED = 11;
    public static final int RECORDER_EXECUTE_STATUS_DISTRIBUTED_NO_EXECUTED = 12;
    public static final int RECORDER_EXECUTE_STATUS_EXECUTING = 13;
    public static final int RECORDER_EXECUTE_STATUS_SUCCESS = 2;
    public static final int RECORDER_EXECUTE_STATUS_FAILED = 3;
    public static final int RECORDER_EXECUTE_STATUS_PARTIAL_SUCCESS = 4;
    public static final int RECORDER_EXECUTE_STATUS_IGNORE = 5;

    public static final int RUNNER_CHANNEL_TYPE_WEB_UI = 1;
    public static final int RUNNER_CHANNEL_TYPE_WEB_API = 2;

    public static final int BATCH_IMPORT_CASE_INITIAL = 0;
    public static final int BATCH_IMPORT_CASE_ALL_SUCCESSFUL = 1;
    public static final int BATCH_IMPORT_CASE_PARTIALLY_SUCCESSFUL = 2;
    public static final int BATCH_IMPORT_CASE_ALL_FAILED = 3;
    public static final int BATCH_IMPORT_CASE_PROCESSING = 4;
    public static final int BATCH_IMPORT_CASE_EXCEPTION = 7;

    public static final int BATCH_IMPORT_CASE_TEMPLATE_TYPE_SIMPLE = 0;
    public static final int BATCH_IMPORT_CASE_TEMPLATE_TYPE_STANDARD = 1;

    public static final int TestcaseLib_Project =1;

    public static final int TEST_CASE_COMPOSITE_TYPE_SINGLE = 1;
    public static final int TEST_CASE_COMPOSITE_TYPE_ACTION = 2;
    public static final int TEST_CASE_COMPOSITE_TYPE_ACTION_NODE = 3;


    public static final int SCENE_SAVE_MODE_JUST_SCENE = 1;
    public static final int SCENE_SAVE_MODE_INCLUDING_CASE = 2;

}
