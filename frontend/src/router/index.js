// eslint-disable-next-line
/* eslint-disable */
import Vue from 'vue';
import Router from 'vue-router';
import Login from '@/login/Login';

import SupplyProcurement from '@/components/SupplyProcurement';
import Worksheet from '@/components/Worksheet';
import UsingAmbulance from '@/components/UsingAmbulance';
import AmbulanceExpense from '@/components/AmbulanceExpense';
import RepairRecord from '@/components/RepairRecord';
import AmbulancePurchase from '@/components/AmbulancePurchase';




Vue.use(Router);

export default new Router({
    routes: [{
            path: '/',
            name: 'Login',
            component: Login
        }, {
            path: '/SupplyProcurement',
            name: 'SupplyProcurement',
            component: SupplyProcurement
        }, {
            path: '/Worksheet',
            name: 'Worksheet',
            component: Worksheet
        }, {
            path: '/UsingAmbulance',
            name: 'UsingAmbulance',
            component: UsingAmbulance
        }, {
            path: '/AmbulanceExpense',
            name: 'AmbulanceExpense',
            component: AmbulanceExpense
        }, {
            path: '/RepairRecord',
            name: 'RepairRecord',
            component: RepairRecord
        }, {
            path: '/AmbulancePurchase',
            name: 'AmbulancePurchase',
            component: AmbulancePurchase
        }

    ]
});