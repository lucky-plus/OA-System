Ext.define('Admin.view.main.Main', {
    extend: 'Ext.container.Viewport',

    requires: [
        'Ext.button.Segmented',
        'Ext.list.Tree'
    ],

    controller: 'main',
    viewModel: 'main',

    cls: 'sencha-dash-viewport',
    itemId: 'mainView',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    listeners: {
        render: 'onMainViewRender'
    },

    items: [
        {
            xtype: 'toolbar',
            cls: 'sencha-dash-dash-headerbar shadow',
            height: 64,
            itemId: 'headerBar',
            items: [
                {
                    xtype: 'component',
                    reference: 'senchaLogo',
                    cls: 'sencha-logo',
                    html: '<div class="main-logo"><img src="resources/images/company-logo.png">OA_System</div>',
                    width: 250
                },
                {
                    margin: '0 0 0 8',
                    ui: 'header',
                    iconCls:'x-fa fa-navicon',
                    id: 'main-navigation-btn',
                    handler: 'onToggleNavigationSize'
                },
                '->',
                {
                    iconCls: 'x-fa fa-leanpub',
                    ui: 'header',
                    href: '#notice',
                    hrefTarget: '_self',
                    tooltip: '查看公告'
                },
                {
                    iconCls: 'x-fa fa-tasks',
                    ui: 'header',
                    href: '#mytask',
                    hrefTarget: '_self',
                    tooltip: '查看任务'
                },
                {
                    iconCls: 'x-fa fa-paper-plane',
                    ui: 'header',
                    href: '#log',
                    hrefTarget: '_self',
                    tooltip: '查看日志'
                },
                {
                    iconCls:'x-fa fa-th-large',
                    ui: 'header',
                    href: '#profile',
                    hrefTarget: '_self',
                    tooltip: '查看个人中心'
                },
                {
                    xtype: 'tbtext',
                    text: loginUser,
                    ui: 'header',
                    cls: 'top-user-name'
                },
                {
                	ui:'header',
                	reference:'logoutButton',
                	hrefTarget: '_self',
                	tooltip:'注销',
                	iconCls:'x-fa fa-sign-out',
                	listeners:{click:'onLogoutButton'}
                }
            ]
        },
        {
            xtype: 'maincontainerwrap',
            id: 'main-view-detail-wrap',
            reference: 'mainContainerWrap',
            flex: 1,
            items: [
                {
                    xtype: 'treelist',
                    reference: 'navigationTreeList',
                    itemId: 'navigationTreeList',
                    ui: 'nav',
                    store: 'NavigationTree',
                    width: 250,
                    expanderFirst: false,
                    expanderOnly: false,
                    listeners: {
                        selectionchange: 'onNavigationTreeSelectionChange'
                    }
                },
                {
                    xtype: 'container',
                    flex: 1,
                    reference: 'mainCardPanel',
                    cls: 'sencha-dash-right-main-container',
                    itemId: 'contentPanel',
                    layout: {
                        type: 'card',
                        anchor: '100%'
                    }
                }
            ]
        }
    ]
});
