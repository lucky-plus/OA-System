Ext.define('Admin.view.notice.noticeCompose', {
    extend: 'Ext.form.Panel',
    alias: 'widget.noticeCompose',
    requires: [
        'Ext.button.Button',
        'Ext.form.field.Text',
        'Ext.form.field.File',
        'Ext.form.field.HtmlEditor'
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
            xtype: 'textfield',
            fieldLabel: '标题：'
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
            fieldLabel: '正文：'
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
                //handler: 'onComposeDiscardClick'
            },
            {
                xtype: 'button',
                ui: 'gray',
                text: '存为草稿'
            },
            {
                xtype: 'button',
                ui: 'soft-green',
                text: '发布'
            }
        ]
    }
});