package db.changelog.PZZ101

databaseChangeLog {

  changeSet(id: 'PZZ101: Create ingredient table', author: 'jdaze') {
    preConditions(onFail: 'MARK_RAN') {
      not() {
        tableExists(tableName: 'ingredient')
      }
    }
    createTable(tableName: 'ingredient') {
      column(name: 'id', type: 'bigint', autoIncrement: true) {
        constraints(nullable: false, primaryKey: true)
      }

      column(name: 'name', type: 'varchar(255)') {constraints(nullable: false)}
      column(name: 'notes', type: 'varchar(255)') {}
    }

    createSequence(sequenceName: 'SEQ_ingredient', startValue: '1')
  }

  changeSet(id: 'PZZ101: Create pizza table', author: 'jdaze') {
    preConditions(onFail: 'MARK_RAN') {
      not() {
        tableExists(tableName: 'pizza')
      }
    }
    createTable(tableName: 'pizza') {
      column(name: 'id', type: 'bigint', autoIncrement: true) {
        constraints(nullable: false, primaryKey: true)
      }

      column(name: 'name', type: 'varchar(255)') {constraints(nullable: false)}
      column(name: 'is_vegetarian', type: 'boolean') {}
    }

    createSequence(sequenceName: 'SEQ_pizza', startValue: '1')
  }

  changeSet(id: 'PZZ101: Create unit_of_measure table', author: 'jdaze') {
    preConditions(onFail: 'MARK_RAN') {
      not() {
        tableExists(tableName: 'unit_of_measure')
      }
    }
    createTable(tableName: 'unit_of_measure') {
      column(name: 'id', type: 'bigint', autoIncrement: true) {
        constraints(nullable: false, primaryKey: true)
      }

      column(name: 'name', type: 'varchar(255)') {constraints(nullable: false)}
    }

    createSequence(sequenceName: 'SEQ_cell_code', startValue: '1')
  }

  changeSet(id: 'PZZ101: Create pizza_ingredient table', author: 'jdaze') {
    preConditions(onFail: 'MARK_RAN') {
      not() {
        tableExists(tableName: 'pizza_ingredient')
      }
    }
    createTable(tableName: 'pizza_ingredient') {
      column(name: 'id', type: 'bigint', autoIncrement: true) {
        constraints(nullable: false, primaryKey: true)
      }

      column(name: 'pizza_id', type: 'bigint') {constraints(nullable: false)}
      column(name: 'ingredient_id', type: 'bigint') {constraints(nullable: false)}
      column(name: 'unit_of_measure_id', type: 'bigint') {constraints(nullable: false)}
      column(name: 'quantity', type: 'double') {constraints(nullable: false)}
    }

    addForeignKeyConstraint(constraintName: 'FK_pizza_ingredient_on_pizza_id',
        baseTableName: 'pizza_ingredient', baseColumnNames: 'pizza_id',
        referencedTableName: 'pizza', referencedColumnNames: 'id')

    addForeignKeyConstraint(constraintName: 'FK_pizza_ingredient_on_ingredient_id',
        baseTableName: 'pizza_ingredient', baseColumnNames: 'ingredient_id',
        referencedTableName: 'ingredient', referencedColumnNames: 'id')

    addForeignKeyConstraint(constraintName: 'FK_pizza_ingredient_on_unit_of_measure_id',
        baseTableName: 'pizza_ingredient', baseColumnNames: 'unit_of_measure_id',
        referencedTableName: 'unit_of_measure', referencedColumnNames: 'id')

    createSequence(sequenceName: 'SEQ_business_type', startValue: '1')
  }

}