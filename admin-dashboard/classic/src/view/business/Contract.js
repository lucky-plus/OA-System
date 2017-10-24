Ext.define('Admin.view.business.Contract', {
    extend: 'Ext.container.Container',
    xtype: 'contract',

    layout: 'fit',
    // controller: 'xiaotingziViewController',
    // html: '<p>合同管理页面</p>'
    viewModel : {type: 'contractViewModel'},
	margin: '20 20 20 20',
    items:[
        {
            xtype:'contractGrid'
        }
    ]

});
