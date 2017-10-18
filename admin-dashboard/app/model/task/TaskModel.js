Ext.define('Admin.model.task.TaskModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'taskId' ,type: 'int'},
		{name:'createId' ,type: 'int'},
		{name:'userId' ,type: 'int'},
        {name:'taskName' ,type: 'string'},
        {name:'taskText' ,type: 'string'},
        {name:'createDate' ,type: 'date'},
        {name:'completeDate' ,type: 'string'},
		{name:'taskState' ,type: 'string'},
		{name:'createName' ,type: 'string'},
		{name:'userName' ,type: 'string'},
        {name:'realName' ,type: 'string'}
    ]
});
