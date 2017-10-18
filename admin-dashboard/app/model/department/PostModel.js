Ext.define('Admin.model.department.PostModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'postId'			,type: 'int'},
        {name:'postName' ,type: 'string'},
		{name:'deptName' ,type: 'string'},
		{name:'postDescribe' ,type: 'string'}
    ]
});
