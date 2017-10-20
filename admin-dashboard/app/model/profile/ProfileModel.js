Ext.define('Admin.model.profile.ProfileModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'userId',				type: 'string'},
		{name:'realName' ,			type: 'string'},
		{name:'password' ,			type: 'string'},
		{name:'rePassword',			type: 'string'},
        {name:'sex' ,				type: 'string'},
        {name:'nativePlace',		type: 'string'},
        {name:'idType',				type: 'string'},
        {name:'mail',				type: 'string'},
        {name:'idNumber',			type: 'string'},
        {name:'home',				type: 'string'},
		{name:'birthday',			type: 'date'},
		{name:'onDutDate',			type: 'date'},
		{name:'qq_number',			type: 'int'},
		{name:'postId',				type: 'string'},
		{name:'roleId',				type: 'string'},
		{name:'wechatNumber',		type: 'string'},
		{name:'mobilePhone',		type: 'string'}
    ]
});
