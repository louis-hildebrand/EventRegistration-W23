<template>
  <div id="home-page">
    <h2 class="table-title">Users</h2>
    <table>
      <tr>
        <th>Name</th>
        <th>Creation date</th>
      </tr>
      <tr v-for="p in persons">
        <td>{{ p.name }}</td>
        <td>{{ p.creationDate }}</td>
      </tr>
      <!-- <tr>
        <td>John</td>
        <td>2023/03/31</td>
      </tr> -->
    </table>
    <h2>New User</h2>
    <input type="text" placeholder="Name" v-model="newPersonName">
    <input type="password" placeholder="Password" v-model="newPersonPassword">
    <button v-bind:disabled="createUserButtonDisabled" @click="createUser()">Create</button>
    <p class="error">{{ errorMsg }}</p>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data() {
    return {
      persons: [
        {name: 'John', creationDate: '2023/03/31'},
        {name: 'Alice', creationDate: '2022/12/31'},
      ],
      newPersonName: '',
      newPersonPassword: '',
      errorMsg: '',
    };
  },
  methods: {
    createUser() {
      if (this.newPersonName === 'C3PO') {
        this.errorMsg = 'Droids are not allowed.';
        return;
      }
      
      const now = new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, 0);
      const day = String(now.getDate()).padStart(2, 0);
      const dateStr = `${year}/${month}/${day}`;
      const person = {name: this.newPersonName, creationDate: dateStr}
      this.persons.push(person);
      
      this.newPersonName = '';
      this.newPersonPassword = '';
      this.errorMsg = '';
    }
  },
  computed: {
    createUserButtonDisabled() {
      return !this.newPersonName.trim() || !this.newPersonPassword.trim();
    }
  }
}
</script>

<style>
td, th {
  border: 1px solid black;
  padding: 0.5em;
}

#home-page {
  display: flex;
  flex-direction: column;
  padding: 5%;
  align-items: stretch;
}

.error {
  color: red;
}
</style>
