Ext.define('Admin.view.profile.UserProfile',
        {extend:Admin.view.profile.UserProfileBase,
        xtype:'profile',
        cls:'userProfile-container',
        layout:'responsivecolumn',
        controller: 'profileViewController',
        //viewModel : {type: 'profileViewModel'},

         items:[ {
                        xtype:'profilesocial',
                        userCls:'big-40 small-100 shadow'
                    },
                    {
                        xtype:'profileForm',
                         userCls:'big-60 small-100 shadow'
                    }
                ]

});