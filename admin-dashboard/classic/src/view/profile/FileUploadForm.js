Ext.define('Admin.view.profile.FileUploadForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.fileUploadForm',
    id : "fileUploadForm",
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
    layout: {
        type:'vbox',
        align:'stretch'
    },

    bodyPadding: 10,
    scrollable: true,

    defaults: {
        labelWidth: 100,
        labelSeparator: ''
    },
    items: [{
        xtype: 'hidden',
        fieldLabel: 'userId',
        name:'userId',
        value: loginUserId
    },{
        xtype: 'fileuploadfield',
        fieldLabel: '图片名',
    },,{
           xtype : 'component',
           id : 'browseImage',
           fieldLabel : "预览图片",
           autoEl : {
               width : '100%',
               height : 300,
               tag : 'img',
               src : Ext.BLANK_IMAGE_URL,
               // style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',
               // complete : 'off',
               id : 'imageBrowse'
           }
        }
    ],

    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
            xtype: 'button',
            ui:'soft-blue',
            //ui: 'soft-red',
            text: '上传',
            handler: 'imageFileUpload'
        },{
            xtype: 'button',
            //ui: 'gray',
            text: '取消',
            handler: 'profileGridWindowClose'
        }]
    }
});
