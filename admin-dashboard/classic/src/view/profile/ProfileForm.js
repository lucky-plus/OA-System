Ext.define('Admin.view.profile.ProfileForm', {
    extend: 'Ext.form.Panel',
    xtype: 'profileForm',
    id: 'proForm',

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
    viewModel:{type:'profileViewModel'},


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
        },
        {
        xtype: 'fieldcontainer',
        margin: '30 0 10 0',
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
                xtype: 'radiogroup',
                defaults:{flex:1},
                layout:'hbox',
                name:'sex',
                editable : false,// 是否允许输入
                allowBlank : false,// 不允许为空
                items:[
                    { boxLabel: '男',   name: 'sex', inputValue: '男' },
                    { boxLabel: '女', name: 'sex', inputValue: '女' }
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
                    itemId: 'pass',
                    allowBlank: false,
                    cls:'wizard-form-break'
                },
                {
                    fieldLabel: '再输入一次',
                    anchor: '100%',
                    vtype: 'password',
                    initialPassField: 'pass',
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
            name: 'mail',
            vtype: 'email',
            allowBlank: false
        },{
            fieldLabel: '手机',
            name: 'mobilePhone',
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
                            {"value":"大陆",    "name":"大陆"},
                            {"value":"港澳",  "name":"港澳"},
                            {"value":"国外",     "name":"国外"}
                            ]
                    }),
                    value:'大陆',
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
                    name:'wechatNumber'
                },
                {
                    fieldLabel: 'QQ号码',
                    emptyText:'QQ Number',
                    name:'qq_number'
                }]
    },{
          xtype: 'hidden',
          fieldLabel: 'userName',
            name:'userName'
    },{
          xtype: 'hidden',
          fieldLabel: 'pictureFileName',
            name:'pictureFileName'
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
    }],
      on: function() {
        Ext.Ajax.request({
            url : 'staff/findUserByUserId.json?userId='+loginUserId,
            method : 'post',
            success: function(response) {
                var profile = Ext.util.JSON.decode(response.responseText);
                var proform = Ext.getCmp("proForm");
                proform.items.getAt(0).setValue(profile.userId);
                proform.items.getAt(1).setValue(profile.postId);
                proform.items.getAt(2).setValue(profile.roleId);
                proform.items.getAt(3).items.getAt(0).setValue(profile.realName);
                proform.items.getAt(4).items.getAt(0).setValue(profile.sex);
                proform.items.getAt(5).items.getAt(0).setValue(profile.password);
                proform.items.getAt(6).items.getAt(0).setValue(profile.mail);
                proform.items.getAt(6).items.getAt(1).setValue(profile.mobilePhone);
                proform.items.getAt(7).items.getAt(0).setValue(profile.idType);
                proform.items.getAt(7).items.getAt(1).setValue(profile.idNumber);
                var birthday = profile.birthday;
                proform.items.getAt(8).items.getAt(0).setValue(birthday.replace(/-/g,"/"));
                var onDutDate = profile.onDutDate;
                proform.items.getAt(8).items.getAt(1).setValue(onDutDate.replace(/-/g,"/"));
                proform.items.getAt(9).items.getAt(0).setValue(profile.home);
                proform.items.getAt(10).items.getAt(0).setValue(profile.nativePlace);
                proform.items.getAt(11).items.getAt(0).setValue(profile.wechatNumber);
                proform.items.getAt(11).items.getAt(1).setValue(profile.qq_number);
                proform.items.getAt(12).setValue(profile.userName);
                proform.items.getAt(13).setValue(profile.pictureFileName);
            }
        });
    }
 }
);