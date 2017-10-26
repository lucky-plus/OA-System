Ext.define('Admin.model.personalNotes.PersonalNotesModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'notesId',			type: 'int'},
		{name:'realName' ,			type: 'string'},
		{name:'examineTime',		type: 'date'},
		{name:'notesName',		    type: 'string'},
		{name:'examineResult',		type: 'string'}
    ]
});
