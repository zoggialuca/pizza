package db.changelog.PZZ102

databaseChangeLog {

  changeSet(id: 'PZZ102: add price to pizza table', author: 'jdaze') {
    preConditions(onFail: 'MARK_RAN') {
      not() {
        columnExists(tableName: 'pizza', columnName: 'price')
      }
    }
    addColumn(tableName: 'pizza') {
      column(name: 'price', type: 'double') {}
    }
  }

  changeSet(id: 'PZZ102: create abc table', author: 'jdaze') {
    preConditions(onFail: 'MARK_RAN') {
      not() {
        tableExists(tableName: 'abc')
      }
    }
    createTable(tableName: 'abc') {
      column(name: 'id', type: 'bigint', autoIncrement: true) {
        constraints(nullable: false, primaryKey: true)
      }

      column(name: 'name', type: 'varchar(255)') {constraints(nullable: false)}
      column(name: 'notes', type: 'varchar(255)') {}
    }

    createSequence(sequenceName: 'SEQ_ingredient', startValue: '1')
  }
}
