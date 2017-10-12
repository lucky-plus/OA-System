Ext.define('Admin.model.staff.StaffModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'userId'			,type: 'string'},
		{name:'realName' ,			type: 'string'},
        {name:'sex' ,			type: 'string'},
        {name:'nativePlace'	,type: 'string'},
		{name:'birthday'		,type: 'date'},
		{name:'onDutDate'		,type: 'date'},
		{name:'dept'		,type: 'string'},
		{name:'mobilePhone'		,type: 'string'}
    ]
});
