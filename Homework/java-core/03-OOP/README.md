# OrcoStat - Analytic tool of negatively alive orcs and their equipment

# 1. Business logic

### Measure damage

1. Count destroyed technics and orcs
2. Count total damage by ZSU in dollars

### Model

1. `Orc`
    - Orc types
        1. Orc
        2. Driver
           - has driver license
        3. Pilot
           - has flew hours score
        4. ...TODO...
    - Cost a bit
        - default cost is Lada Vesta Sport price
        - cost can be customized for each orc
        - orc cost can increase during his life
        - scream on death
2. `Technique`
    - Technic types
        1. Tank
        2. Aircraft
        3. ...TODO...
    - Cost a bit. Cost is hardcoded for each type
    - Can shoot. Sound differently
    - Explodes with sound "Destroyed!" and every orc in equipage scream
    - Technic can be with equipage and can be also abandoned
        - Number of technic equipage is dynamic
        - Equipage loss increments orcs as well
        - Equipage size has realistic limits
            - Tank contains from 0 to 6 orcs
            - Orcs are unique and not repeatable
3. `Collection`
    1. Simple implementation of collection
    2. Collection API should provide:
        1. Add new element
        2. Return elements
        3. Get collection size

# 2. Homework requirements

1. All existing tests should pass: be green and not modified
2. Add new technic type - `Ship`
    1. Has equipage
    2. Expensive
    3. Ruled by `Commander` (new orc type)
