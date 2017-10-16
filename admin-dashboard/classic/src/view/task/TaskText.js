Ext.define('Admin.view.notcie.TaskText', {
    extend: 'Ext.form.Panel',
    alias: 'widget.taskText',
	id:'taskText',
    requires: [
        'Ext.form.field.Text',
		'Ext.form.DisplayField'
    ],
	controller: 'taskViewController',
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
		name:'taskName',
		
	},{
		xtype: 'displayfield',
		name:'taskText'
	}
]
	
}
);