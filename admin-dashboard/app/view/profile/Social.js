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
            src: 'resources/images/user-profile/'+pictureFileName
        },
        {
            xtype: 'component',
            cls: 'userProfileName',
            html: loginUserRealName
        },
        {
            xtype: 'component',
            cls: 'userProfileDesc',
            html: loginUserDeptName
        },
        {
            xtype: 'component',
            padding: '0 0 12 0',
            html: loginUserPostName
        },
        {
            xtype: 'button',
            renderTO : Ext.getBody(),
            width: 200,
            text: '头像上传',
            listeners: {
            click: 'showFileUploadFormWindow'
        	}
        }
    ]
});