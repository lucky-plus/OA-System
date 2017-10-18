Ext.define('Admin.view.homePage.HomePage', {
    extend: 'Ext.container.Container',
    xtype: 'homePage',

    requires: [
        'Ext.ux.layout.ResponsiveColumn'
    ],
    layout: 'responsivecolumn',
    items: [
        {
            xtype: 'postGrid',
            userCls: 'big-50 small-100'
        },
        {
            //xtype: 'chartspie3dpanel',
            userCls: 'big-50 small-100'
        },
        {
            xtype: 'noticeGrid',
            userCls: 'big-50 small-100'
        }
    ]
});
