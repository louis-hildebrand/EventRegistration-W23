<template>
  <div id="home-page">
    <h2 class="table-title">Users</h2>
    <table>
      <tr>
        <th>Name</th>
        <th>Creation date</th>
      </tr>
      <tr>
        <td>John</td>
        <td>2023/03/31</td>
      </tr>
    </table>
  </div>
</template>

<script>

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

.table-title {
  color: blue;
}
</style>
