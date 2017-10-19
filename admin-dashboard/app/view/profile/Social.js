Ext.define('Admin.view.profile.Social', {
    extend: 'Ext.panel.Panel',
    xtype: 'profilesocial',

    requires: [
        'Ext.Button',
        'Ext.Container'
    ],

    layout: {
        type: 'vbox',
        align: 'middle'
    },
    cls: 'timeline-items-wrap user-profile-desc',

    height: 320,
    bodyPadding: 20,
    items: [
        {
            xtype: 'image',
            cls: 'userProfilePic',
            height: 120,
            width: 120,
            alt: 'profile-picture',
            src: 'resources/images/user-profile/20.png'
        },
        {
            xtype: 'component',
            cls: 'userProfileName',
            height: '',
            html: 'Jessica Warren'
        },
        {
            xtype: 'component',
            cls: 'userProfileDesc',
            html: 'CO-FOUNDER, CEO'
        },
        {
            xtype: 'component',
            html: 'San Jose, CA',
            padding: '0 0 12 0'
        },
        {
            xtype: 'component',
            html: 'Member since 1 years ago',
            padding: '0 0 12 0'
        },
        {
            xtype: 'button',
            renderTO : Ext.getBody(),
            width: 200,
            text: '头像上传',
            platformConfig: {
                classic: {
                    scale: 'large'
                },
                modern: {
                    ui: 'action'
                }
            },
            listeners: {
            click: 'showFileUploadFormWindow'
        	}
        }
    ]
});
