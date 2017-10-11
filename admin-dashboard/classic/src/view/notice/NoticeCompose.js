Ext.define('Admin.view.notice.NoticeCompose', {
    extend: 'Ext.form.Panel',
    alias: 'widget.noticeCompose',
    requires: [
        'Ext.button.Button',
        'Ext.form.field.Text',
        'Ext.form.field.File',
        'Ext.form.field.HtmlEditor',
		'Ext.form.field.Hidden'
    ],

    viewModel: {
        type: 'noticeCompose'
    },

    controller: 'NoticeViewController',

    cls: 'noticeCompose',

    layout: {
        type:'vbox',
        align:'stretch'
    },

    bodyPadding: 10,
    scrollable: true,

    defaults: {
        labelWidth: 60,
        labelSeparator: ''
    },

    items: [
		{
		xtype: 'hidden',
		fieldLabel: 'Id',
		//allowBlank: false,
		name:'noticeId',
		handler:'noticeGridOpenEditWindow'
		},
		{
		xtype: 'hidden',
		fieldLabel: 'userId',
		//allowBlank: false,
		name:'userId',
		value: loginUserId
		},
        {
            xtype: 'textfield',
            fieldLabel: '标题：',
			name:'noticeName'
        },
        {
            xtype: 'htmleditor',
            
            // Make tips align neatly below buttons.
            buttonDefaults: {
                tooltip: {
                    align: 't-b',
                    anchor: true
                }
            },
            flex: 1,
            minHeight: 100,
            labelAlign: 'top',
            fieldLabel: '正文：',
			fontFamilies: ["宋体", "隶书", "黑体"],
			name:'noticeText'
        }
    ],

    bbar: {
        overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                ui: 'soft-red',
                text: '关闭',
                handler: 'noticeGridWindowsClose'
            },
            {
                xtype: 'button',
                ui: 'soft-green',
                text: '发布',
				handler:'noticeGridTextSubmit'
            }
        ]
    }
});