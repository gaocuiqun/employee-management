export enum PredicateType {
  UNKNOWN_PREDICATE = 0,
  EQ = 1, // =
  LT = 2, // <
  GT = 3, // >
  LE = 4, // <=
  GE = 5, // >=
  BETWEEN = 6, //
  LIKE = 7,
  NE = 8, // !=
  IS_NULL = 9,
  IS_NOT_NULL = 10,
  IN = 11,
  NOT_IN = 12
}

export enum LogicalConnectionType {
  AND = 0,
  OR = 1
}

export enum OrderType {
  ASC = 0,
  DESC = 1
}

export class LogicalPredicateVo {
  constructor(
    public readonly predicateType: PredicateType,
    public readonly fieldName: string,
    public readonly paramNames: string[]) { }
}

export class FilterPredicate {
  public predicate: LogicalPredicateVo;
  public connection: LogicalConnectionVo;
}

export class LogicalConnectionVo {
  constructor(
    public readonly logicalConnectionType: LogicalConnectionType,
    public readonly predicates: FilterPredicate[]
  ) {  }
}

export class OrderBy {
  constructor(
    public readonly fieldName: string,
    public readonly order: OrderType
  ) { }
}

export class QueryCommand {
  public predicate: FilterPredicate;
  public params: Map<string, string>;
  public pageNumber: number;
  public rowsPerPage: number;
  public orderBy: OrderBy[];
  public pagingState: string;
}

export class SearchCommand {
  constructor(public readonly q: string) { }
}

export function createConnection(connectionType: LogicalConnectionType, predicates: FilterPredicate[]): FilterPredicate {
  const predicate = new FilterPredicate();
  predicate.connection = new LogicalConnectionVo(connectionType, predicates);
  return predicate;
}

export function createPredicate(predicateType: PredicateType, fieldName: string, fieldAlias: string, fieldValue: any,
  params: Map<string, string>): FilterPredicate {
  const paramNames = [fieldAlias];
  params[fieldAlias] = fieldValue;
  const predicate = new FilterPredicate();
  predicate.predicate = new LogicalPredicateVo(predicateType, fieldName, paramNames);
  return predicate;
}
