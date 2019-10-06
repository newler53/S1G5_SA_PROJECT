<template>
<div><nbH_ER></nbH_ER>
  <div class="from-AmbulanceExpense">
    <form>
      <p class="h2 text-center mb-4">บันทึกค่าใช้จ่ายสำหรับรถพยาบาล</p>

      <label>ชื่อพนักงาน</label>
      <select v-model="employee_id"  class="browser-default custom-select">
        <option value="null" disabled> เลือกชื่อพนักงาน </option>
        <option  v-for="emp in employee"  :key="emp.id" :value="emp.id" > {{ emp.name }} </option>
      </select>
      <br />
      <br />

      <label>รหัสการใช้งานรถพยาบาล</label>
      <select v-model="usingAmbulance_id"  class="browser-default custom-select" @change="setUsingAmbulanceDetail">
        <option value="null" disabled> เลือกรหัสการใช้งานรถพยาบาล </option>
        <option  v-for="ua in usingAmbulance"  :key="ua.id" :value="ua.id" > {{ ua.id }} </option>
      </select>
      <div v-if="check" class="UsingAmbulanceDetail">
        <h5> รายละเอียด: </h5>
        <label><b>usingDate: </b> {{usingAmbulance_usingDate}} </label><br>
        <label><b>createdBy: </b> {{usingAmbulance_createdBy}} </label><br>
        <label><b>vehicle: </b> {{usingAmbulance_vehicle}} </label><br>
        <label><b>usingType: </b> {{usingAmbulance_usingType}} </label><br>
        <hr>
      </div>
      <br />
      <br />

      <label>ประเภทค่าใช้จ่าย</label>
      <select v-model="expenseType_id"  class="browser-default custom-select">
        <option value="null" disabled> เลือกประเภทค่าใช้จ่าย </option>
        <option  v-for="et in expenseType"  :key="et.id" :value="et.id" > {{ et.type }} </option>
      </select>
      <br />
      <br />

      <label>งบประมาณ</label>
      <input v-model="budget" type="text" class="form-control" />
      <br />

      <label>*note</label>
      <textarea v-model="note" class="form-control" rows="5"></textarea>
      <div class="text-center mt-4">
        <button class="btn btn-unique" @click="checkList" >บันทึกข้อมูลค่าใช้จ่าย</button>
      </div>
    </form>
  </div>
</div>
</template>

<script>
import nbH_ER from '@/navbar/nbH_ER';
import http from "../http-common";
export default {
  name:"AmbulanceExpense",
  components: {
    nbH_ER
  },
  data() {
    return {
      employee: [],
      check: false,
      usingAmbulance:[],
      usingAmbulance_usingDate:'',
      usingAmbulance_createdBy:'',
      usingAmbulance_vehicle:'',
      usingAmbulance_usingType:'',
      expenseType:[],
      employee_id:'null',
      usingAmbulance_id:'null',
      expenseType_id:'null',
      budget:'',
      note:'',
    };
  },
  methods:{
    getEmployee() {
      http
        .get("/employees/")
        .then(response => {
          this.employee = response.data;
          this.$nextTick();
          console.log(this.employee);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getUsingAmbulance() {
      http
        .get("/usingAmbulance/")
        .then(response => {
          this.usingAmbulance = response.data;
          this.$nextTick();
          console.log(this.usingAmbulance);
        })
        .catch(e => {
          console.log(e);
        });
    },
    setUsingAmbulanceDetail(){
      for (let elem in this.usingAmbulance) {
          if(this.usingAmbulance[elem].id == this.usingAmbulance_id){
              this.employeeName = this.usingAmbulance[elem].usingDate;
              this.usingAmbulance_usingDate = this.usingAmbulance[elem].usingDate;
              this.usingAmbulance_createdBy = this.usingAmbulance[elem].createdBy.name;
              this.usingAmbulance_vehicle = this.usingAmbulance[elem].vehicle.am_Code;
              this.usingAmbulance_usingType = this.usingAmbulance[elem].usingType.type;
          }
      }
      this.check = true;
    },
    getExpenseType() {
      http
        .get("/expenseType/")
        .then(response => {
          this.expenseType = response.data;
          this.$nextTick();
          console.log(this.expenseType);
        })
        .catch(e => {
          console.log(e);
        });
    },
    checkList(){
      if(this.employee_id == 'null' || this.usingAmbulance_id == 'null' || this.expenseType_id == 'null'){
          alert("กรุณาเลือกข้อมูล");
      }else if(this.budget == ''){
          alert("กรุณากรอกข้อมูลเลือกข้อมูล");
      }else if(this.expenseType_id == 3 && this.note == ''){
           alert("กรุณากรอกข้อมูล *note");
      }else{
          if(this.note == ''){
            this.note=' ';
          }
          this.saveAmbulanceExpense();
      }
    },
    saveAmbulanceExpense(){
      http
        .post("/ambulanceExpense/"+
          this.budget+
          "/" +this.note +
          "/" +this.employee_id +
          "/" +this.expenseType_id +
          "/" +this.usingAmbulance_id
        )

        .then(response => {
          console.log(response);
          alert("บันทึกข้อมูลสำเร็จ");

        })
        .catch(e => {
          console.log(e);
          alert("บันทึกข้อมูลไม่สำเร็จ เนื่องจาก:"+e);
        });
    }
  },
  mounted(){
    this.getEmployee();
    this.getUsingAmbulance();
    this.getExpenseType();

  }
};
</script>
<style scoped>
.from-AmbulanceExpense {
  margin: 100px 600px;
}
.UsingAmbulanceDetail{
  margin-left: 20px;
  margin-top: 20px;
}
</style>
