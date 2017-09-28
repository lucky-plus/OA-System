Ext.define('Admin.model.notice.NoticeModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'noticeId',type: 'int'},
        {name:'noticeName' ,type: 'string'},
        {name:'noticeTime'	,type: 'date'},
		{name:'userId',type: 'string'}
    ]
});
