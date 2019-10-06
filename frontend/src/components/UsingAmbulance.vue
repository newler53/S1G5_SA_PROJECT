<template>
<div><nbDRIVER></nbDRIVER> 
     <div class="from-UsingAmbulance">
       
         <!-- Default form contact -->
         <p class="h4 mb-4">ระบบบันทึกการใช้งานรถพยาบาล</p>
         <p class="h10 mb-4">พนักงาน</p>
         <select class="browser-default custom-select mb-4" name="employee_id"
                 v-model="employee_id"
                 @change="onChange($event)"
                 :required="true">

             <option :value="null"
                     disabled
                     :selected="true">
                 เลือกรหัสพนักงาน
             </option>

             <option v-for="emp in employees"
                     v-bind:key="emp.id"
                     v-bind:value="emp.id">
                 id::{{ emp.id }}  ||  {{ emp.name }}
             </option>
         </select>
         <p class="h10 mb-4">รถพยาบาล</p>
         <select class="browser-default custom-select mb-4" name="amvehicle_id" v-model="amvehicle_id" @change="onChange($event)" :required="true">
             <option :value="null" disabled :selected="true">เลือกรหัสรถพยาบาล</option>
             <option v-for="vehi in vehicles"
                     v-bind:key="vehi.id"
                     v-bind:value="vehi.id">
                 {{ vehi.id }}
             </option>
         </select>
         <p class="h10 mb-4">ประเภทการใช้งาน</p>
         <select class="browser-default custom-select mb-4" aria-placeholder="เลือกประเภทการใช้งาน" name="ptype_id" v-model="ptype_id" @change="onChange($event)" :required="true">
             <option :value="null" disabled :selected="true">เลือกประเภทการใช้งาน</option>
             <option v-for="pty in purchaseType"
                     v-bind:key="pty.id"
                     v-bind:value="pty.id">
                 {{ pty.type }}
             </option>
         </select>
         <!-- Send button -->
         <center>
            <button class="btn btn-success" type="submit" style="width:60%" @click="saveUsingAmbulance">บันทึก</button>
         </center>
         <!-- Default form contact -->
     </div>
</div>
</template>

<script>
import axi from "../http-common";
import nbDRIVER from '@/navbar/nbDRIVER';
export default {
  name: 'UsingAmbulance',
  components: {
    nbDRIVER
  },
  data () {
    return {
      employees:[],
      vehicles:[],
      purchaseType:[],  
        employee_id: "",
        amvehicle_id: "",
        ptype_id: "",
      valid: false,
    };
  },
  methods:{
    getEmployees() {
      axi
        .get("/employees/")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
    },
    getVehicles() {
      axi
        .get("/vehicles/")
        .then(response => {
          this.vehicles = response.data;
          console.log(response.data);
        })
    },
    getPurchaseType() {
      axi
        .get("/usingAmbulance/usingType/")
        .then(response => {
          this.purchaseType = response.data;
          console.log(response.data);
        })
    },
    onChange(event) {
      if(this.employee_id && this.amvehicle_id && this.ptype_id && this.pdate)
        this.valid = true;
      else
        this.valid = false;
    },
    saveUsingAmbulance(){
        axi
          .post(
            "/usingAmbulance/" +
              this.employee_id +
              "/" +
              this.amvehicle_id +
              "/" +
              this.ptype_id 
              ,this.SupplyProcurement
          )
          .then(response => {
            console.log(response);
            // location.href="http://localhost:9000/usingAmbulance/"
            alert("Save successfully");
            location.href="http://localhost:8080/#/UsingAmbulance"
          })
      },
      refreshList() {
        this.getEmployees();
        this.getVideos();
        this.getRentalTypes();
      },
  },
  mounted(){
    this.getEmployees(),
    this.getVehicles(),
    this.getPurchaseType()
  }
}
</script>

<style>
.from-UsingAmbulance {
    width: 600px;
    height: 500px;
    margin: 100px auto;
    position: relative;
}
</style>