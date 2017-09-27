Ext.define('Admin.view.notcie.NoticeText', {
    extend: 'Ext.form.Panel',
    alias: 'widget.noticeText',
    requires: [
        'Ext.form.field.Text',
		'Ext.form.DisplayField'
    ],
	controller: 'NoticeViewController',
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
	 items: [
		{
		xtype: 'displayfield',
		name:'noticeName',
		style:'font-color:20px'
		
	},{
		xtype: 'displayfield',
		name:'noticeText'
	}
]
	
}
);