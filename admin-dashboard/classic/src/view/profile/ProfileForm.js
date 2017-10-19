Ext.define('Admin.view.profile.ProfileForm', {
    extend: 'Ext.form.Panel',
    xtype: 'profileForm',
    requires: [
                'Ext.button.Button',
                'Ext.form.field.Text',
                'Ext.form.field.File',
                'Ext.form.field.HtmlEditor',
                'Ext.form.field.TextArea',
                'Ext.form.field.Time',
                'Ext.form.field.ComboBox',
                'Ext.form.field.Date',
                'Ext.form.field.Radio',
                'Ext.form.field.Hidden'
            ],

    controller: 'profileViewController',
    //viewModel:{type:'wizardFormModel'},

    fieldDefaults: {
        labelAlign: 'right',
        labelWidth: 90,
        msgTarget: Ext.supports.Touch ? 'side' : 'qtip'
    },

    defaults: {
        border: false,
        xtype: 'panel',
        layout: 'anchor'
    },


    //renderTo: Ext.getBody(),
    bodyPadding: 5,
    defaultType: 'textfield',

     items: [{
          xtype: 'hidden',
          fieldLabel: 'userId',
            //allowBlank: false,
            name:'userId'
        },{
          xtype: 'hidden',
          fieldLabel: 'postId',
            //allowBlank: false,
            name:'postId'
        },{
          xtype: 'hidden',
          fieldLabel: 'roleId',
            //allowBlank: false,
            name:'roleId'
        },{
        xtype: 'fieldcontainer',
        margin: '30 0 10 0',
        fieldLabel: '名字',
        layout:'hbox',
        combineErrors: true,
        defaultType: 'textfield',

         defaults:{
            submitEmptyText:false,
            margin: '0 30 0 0',
            hideLabel:'true',
            anchor:'90%'
          },
        items: [{
            fieldLabel: '名字',
            emptyText:'Name',
            flex:1,
            allowBlank: false,
            name: 'realName'
        }]
    }, {
                fieldLabel:'性别',
                xtype:'fieldcontainer',
                margin: '0 0 30 0',
                cls:'wizard-form-break',
                defaultType:'radiofield',
                defaults:{flex:1},
                layout:'hbox',
                name:'sex',
                editable : false,// 是否允许输入
                allowBlank : false,// 不允许为空
                value : 'man',
                items:[
                {
                    boxLabel:'男',
                    value : 'man',
                    inputValue:'Free'
                },
                {
                    boxLabel:'女',
                    value : 'woman',
                    inputValue:'Perosnal'
                }
                ]

    },{
            xtype: 'container',
            layout: 'hbox',
            defaultType: 'textfield',
            margin: '0 0 30 0',
            defaults:{
                labelWidth:90,
                submitEmptyText:false,
                flex: 1,
                anchor:'70%'
                },
            items:[{
                    fieldLabel:'密码',
                    anchor: '100%',
                    emptyText:'Enter a password',
                    inputType:'password',
                    name:'password',
                    cls:'wizard-form-break'
                },
                {
                    fieldLabel: '再输入一次',
                    anchor: '100%',
                    emptyText:'Passwords must match',
                    name:'rePassword',
                    inputType:'password'
                }]
    },{
            xtype: 'container',
            layout: 'hbox',
            defaultType: 'textfield',
            margin: '0 0 30 0',
            defaults:{
                labelWidth:90,
                submitEmptyText:false,
                flex: 1,
                anchor:'95%'
                },
        items: [{
            fieldLabel: '邮箱',
            emptyText : 'Email Address',
            name: 'email',
            vtype: 'email',
            allowBlank: false
        },{
            fieldLabel: '手机',
            name: 'phone',
            emptyText: 'xxx-xxx-xxxx',
            maskRe: /[\d\-]/,
            regex: /^\d{3}-\d{3}-\d{4}$/,
            regexText: 'Must be in the format xxx-xxx-xxxx'
        }]
    },{
            xtype: 'container',
            layout: 'hbox',
            defaultType: 'textfield',
            margin: '0 0 30 0',
            defaults:{
                labelWidth:90,
                submitEmptyText:false,
                flex: 1,
                },
            items:[{
                    fieldLabel: '身份证类型',
                    xtype: 'combobox',
                    name:'idType',
                    store:  Ext.create('Ext.data.Store', {
                        fields: ['value', 'name'],
                        data : [
                            {"value":"HIGH",    "name":"大陆"},
                            {"value":"MEDIUM",  "name":"港澳"},
                            {"value":"LOW",     "name":"国外"}
                                ]
                    }),
                    value:'HIGH',
                    queryMode:    'local',
                    displayField: 'name',
                    valueField:   'value'
                },
                {
                    fieldLabel: '身份证号码',
                    name:'idNumber'
                }]
    },{
            xtype: 'container',
            layout: 'hbox',
            defaultType: 'datefield',
            margin: '0 0 30 0',
            defaults:{
                labelWidth:90,
                submitEmptyText:false,
                flex: 1,
                },
            items:[{
                    fieldLabel: '生日',
                    xtype: 'datefield',
                    format: 'Y/m/d H:i:s',
                    editable:false,//禁止手工修改
                    value : '2017-01-01',
                    name:'birthday'
                },
                {
                    fieldLabel: '入职时间',
                    xtype: 'datefield',
                    format: 'Y/m/d H:i:s',
                    editable:false,//禁止手工修改
                    value : '2017-01-01',
                    name:'onDutDate'
                }]
    },{
            xtype: 'container',
            layout: 'hbox',
            defaultType: 'textfield',
            margin: '0 0 30 0',
            defaults:{
                labelWidth:90,
                submitEmptyText:false,
                flex: 1,
                anchor:'95%'
                },
        items: [{
                    fieldLabel:'家庭住址',
                    name:'home',
                    emptyText:'Address'
                }]
    },{
            xtype: 'container',
            layout: 'hbox',
            defaultType: 'textfield',
            margin: '0 0 30 0',
            defaults:{
                labelWidth:90,
                submitEmptyText:false,
                flex: 1,
                },
            items:[{
                    fieldLabel:'籍贯',
                    name:'nativePlace',
                    emptyText:'City'
                },
                {
                    fieldLabel:'邮政编码',
                    emptyText: 'xxxxxxxx',
                    name:'postalCode'
                }]
    },{
            xtype: 'container',
            layout: 'hbox',
            defaultType: 'textfield',
            margin: '0 0 30 0',
            defaults:{
                labelWidth:90,
                submitEmptyText:false,
                flex: 1,
                },
            items:[{
                    fieldLabel: '微信号码',
                    emptyText:'WeChat Number',
                    vtype:'email',
                    name:'wechatNumber'
                },
                {
                    fieldLabel: 'QQ号码',
                    emptyText:'QQ Number',
                    vtype:'email',
                    name:'qq_number'
                }]
    }
    ],

    buttons: ['->', {
        text: '保存',
        listeners: {
            click: 'saveInfomationSubmit'
        }
    }, {
        text: '上传头像',
        //hanlder:
        listeners: {
            click: 'showFileUploadFormWindow'
        }
    }]
});