Ext.define('Admin.view.business.ContractViewModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.contractViewModel',

    stores: {
        contractLists: {
            //Store reference
            type: 'contractStore',

            //Auto load
            autoLoad: true
        }
    }
});
